package com.homebrew.persistance.helper;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;

import java.util.*;

public class CriteriaHelper {

    private final Criteria criteria;
    private final List<String> aliasList = new ArrayList<>();

    public CriteriaHelper(final Criteria criteria) {
        this.criteria = criteria;
    }

    private static String sanitizeString(String value) {
        return sanitizeString(value, true);
    }

    private static String sanitizeString(String value, boolean escapeDoubleQuotes) {
        if (value == null) {
            return null;
        }

        StringBuilder sBuilder = new StringBuilder(value.length() * 11 / 10);

        int stringLength = value.length();

        for (int i = 0; i < stringLength; ++i) {
            char c = value.charAt(i);

            switch (c) {
                case 0: /* Must be escaped for 'mysql' */
                    sBuilder.append('\\');
                    sBuilder.append('0');

                    break;

                case '\n': /* Must be escaped for logs */
                    sBuilder.append('\\');
                    sBuilder.append('n');

                    break;

                case '\r':
                    sBuilder.append('\\');
                    sBuilder.append('r');

                    break;

                case '\\':
                    sBuilder.append('\\');
                    sBuilder.append('\\');

                    break;

                case '\'':
                    sBuilder.append('\\');
                    sBuilder.append('\'');

                    break;

                case '"': /* Better safe than sorry */
                    if (escapeDoubleQuotes) {
                        sBuilder.append('\\');
                    }

                    sBuilder.append('"');

                    break;

                case '\032': /* This gives problems on Win32 */
                    sBuilder.append('\\');
                    sBuilder.append('Z');

                    break;

                case '\u00a5':
                case '\u20a9':
                    // escape characters interpreted as backslash by mysql
                    // fall through

                default:
                    sBuilder.append(c);
            }
        }

        return sBuilder.toString();
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void addILikeElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addILikeElement(field, params.get(property).toString());
        }
    }

    public void addILikeElement(final String field, final String value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.ilike(fieldLast, value, MatchMode.ANYWHERE));
        }
    }

    public void addILikeNotBlankElement(final String field, final String value) {
        if (StringUtils.isNotBlank(value)) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.ilike(fieldLast, value, MatchMode.ANYWHERE));
        }
    }

    public void addILikeElement(final String field, final String value, MatchMode matchMode) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.ilike(fieldLast, value, matchMode));
        }
    }

    public void addILikeAndElement(final String field, final List<String> values) {
        if (values != null && !values.isEmpty()) {
            Conjunction conjunction = Restrictions.conjunction();
            String fieldLast = createAliases(field, criteria);
            for (String item : values) {
                conjunction.add(Restrictions.ilike(fieldLast, item, MatchMode.ANYWHERE));
            }
            criteria.add(Restrictions.and(conjunction));
        }
    }

    public void addLikeElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addLikeElement(field, params.get(property).toString());
        }
    }

    public void addLikeElement(final String field, final String value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.like(fieldLast, value, MatchMode.ANYWHERE));
        }
    }

    public void addLikeNotBlankElement(final String field, final String value) {
        if (StringUtils.isNotBlank(value)) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.like(fieldLast, value, MatchMode.ANYWHERE));
        }
    }

    public void addLikeElement(final String field, final String value, MatchMode matchMode) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.like(fieldLast, value, matchMode));
        }
    }

    public void addLikeAndElement(final String field, final List<String> values) {
        if (values != null && !values.isEmpty()) {
            String fieldLast = createAliases(field, criteria);
            for (String item : values) {
                if (StringUtils.isNotEmpty(item)) {
                    criteria.add(Restrictions.like(fieldLast, item, MatchMode.ANYWHERE));
                }
            }
        }
    }

    public void addMatchElement(final String field, final List<String> values) {
        if (values != null && !values.isEmpty()) {
            String fieldLast = createAliases(field, criteria);

            StringBuilder sqlQueryBuilder = new StringBuilder();

            for (String item : values) {
                if (StringUtils.isNotEmpty(item)) {
                    sqlQueryBuilder.append("'+*").append(sanitizeString(item)).append("*'");
                }
            }

            String sqlValues = sqlQueryBuilder.toString();

            if (StringUtils.isNotEmpty(sqlValues)) {
                String sqlCommand = " MATCH (" + fieldLast + ") AGAINST (" + sqlValues + " IN BOOLEAN MODE) ";
                criteria.add(Restrictions.sqlRestriction(sqlCommand));
            }
        }
    }

    public void addNeElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addNeElement(field, params.get(property));
        }
    }

    public void addNeElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.ne(fieldLast, value));
        }
    }

    public void addNeOrNullElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addNeOrNullElement(field, params.get(property));
        }
    }

    public void addNeOrNullElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.or(Restrictions.ne(fieldLast, value), Restrictions.isNull(fieldLast)));
        }
    }

    public void addNeOrNullElementAllowNullValue(final String field, final String property, final Map<String, Object> params) {
        if (params.get(property) != null) {
            addNeOrNullElement(field, params.get(property));
        } else {
            addIsNull(field);
        }
    }

    public void addEqElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addEqElement(field, params.get(property));
        }
    }

    public void addEqElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.eq(fieldLast, value));
        }
    }

    public void addEqNotBlankElement(final String field, final String value) {
        if (StringUtils.isNotBlank(value)) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.eq(fieldLast, value));
        }
    }

    public void addEqCaseInsensitiveElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addEqCaseInsensitiveElement(field, params.get(property));
        }
    }

    public void addEqCaseInsensitiveElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.eq(fieldLast, value).ignoreCase());
        }
    }

    public void addEqNotBlankCaseInsensitiveElement(final String field, final String value) {
        if (StringUtils.isNotBlank(value)) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.eq(fieldLast, value).ignoreCase());
        }
    }

    public void addEqOrNullElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addEqOrNullElement(field, params.get(property));
        }
    }

    public void addEqOrNullElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.or(Restrictions.eq(fieldLast, value), Restrictions.isNull(fieldLast)));
        }
    }

    public void addEqOrNullElementAllowNullValue(final String field, final String property, final Map<String, Object> params) {
        if (params.get(property) != null) {
            addEqOrNullElement(field, params.get(property));
        } else {
            addIsNull(field);
        }
    }

    public void addEqOrEqElement(final String field, final Object value1, final Object value2) {
        if (value1 != null && value2 != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.or(Restrictions.eq(fieldLast, value1), Restrictions.eq(fieldLast, value2)));
        }
    }

    public void addEqOrEqOrNullElement(final String field, final Object value1, final Object value2) {
        if (value1 != null && value2 != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.or(Restrictions.eq(fieldLast, value1), Restrictions.eq(fieldLast, value2), Restrictions.isNull(fieldLast)));
        }
    }

    public void addIsNullElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addIsNull(field);
        }
    }

    public void addIsNull(final String field) {
        if (field != null) {
            criteria.add(Restrictions.isNull(field));
        }
    }

    public void addIsNotNullElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addIsNotNull(field);
        }
    }

    public void addIsNotNull(final String field) {
        if (field != null) {
            criteria.add(Restrictions.isNotNull(field));
        }
    }

    public void addEqOrNullAndNotDiffElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.and(Restrictions.or(Restrictions.eq(fieldLast, value), Restrictions.isNull(fieldLast)), Restrictions.and(Restrictions.isNotNull(fieldLast), Restrictions.eq(fieldLast, value))));
        }
    }

    public void addEqIntegerElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property) && !"0".equals(params.get(property).toString())) {
            addEqElement(field, params.get(property));
        }
    }

    public void addGreaterOrNullElementAllowNullValue(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addGreaterOrNullElement(field, params.get(property));
        } else {
            addIsNull(field);
        }
    }

    public void addGreaterElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.ge(fieldLast, value));
        }
    }

    public void addGreaterOrNullElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addGreaterOrNullElement(field, params.get(property));
        }
    }

    public void addGreaterOrNullElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.or(Restrictions.ge(fieldLast, value), Restrictions.isNull(fieldLast)));
        }
    }

    public void addGreaterOrEqDateElementAllowNullValue(final String field, final String property, final Map<String, Object> params) {
        if (params.get(property) != null) {
            addGreaterOrEqDateElement(field, (Date) params.get(property));
        } else {
            addIsNull(field);
        }
    }

    public void addGreaterOrEqDateElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addGreaterOrEqDateElement(field, (Date) params.get(property));
        }
    }

    public void addGreaterOrEqDateElement(final String field, final Date value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            final Calendar calendar = new GregorianCalendar();
            calendar.setTime(value);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            criteria.add(Restrictions.ge(fieldLast, calendar.getTime()));
        }
    }

    public void addGreaterOrEqDateTimeElement(final String field, final Date value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            final Calendar calendar = new GregorianCalendar();
            calendar.setTime(value);
            criteria.add(Restrictions.ge(fieldLast, calendar.getTime()));
        }
    }

    public void addGreaterOrEqOrNullDateElementAllowNullValue(final String field, final String property, final Map<String, Object> params) {
        if (params.get(property) != null) {
            addGreaterOrEqOrNullDateElement(field, (Date) params.get(property));
        } else {
            addIsNull(field);
        }
    }

    public void addGreaterOrEqOrNullDateElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addGreaterOrEqOrNullDateElement(field, (Date) params.get(property));
        }
    }

    public void addGreaterOrEqOrNullDateElement(final String field, final Date value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            final Calendar calendar = new GregorianCalendar();
            calendar.setTime(value);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            criteria.add(Restrictions.or(Restrictions.ge(fieldLast, calendar.getTime()), Restrictions.isNull(fieldLast)));
        }
    }

    public void addLessOrNullElementAllowNullValue(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addLessOrNullElement(field, params.get(property));
        } else {
            addIsNull(field);
        }
    }

    public void addLessOrNullElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addLessOrNullElement(field, params.get(property));
        }
    }

    public void addLessOrNullElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.or(Restrictions.le(fieldLast, value), Restrictions.isNull(fieldLast)));
        }
    }

    public void addLessOrEqDateElementAllowNullValue(final String field, final String property, final Map<String, Object> params) {
        if (params.get(property) != null) {
            addLessOrEqDateElement(field, (Date) params.get(property));
        } else {
            addIsNull(field);
        }
    }

    public void addLessOrEqDateElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addLessOrEqDateElement(field, (Date) params.get(property));
        }
    }

    public void addLessOrEqDateElement(final String field, final Date value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            final Calendar calendar = new GregorianCalendar();
            calendar.setTime(value);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            criteria.add(Restrictions.le(fieldLast, calendar.getTime()));
        }
    }

    public void addLessOrEqDateTimeElement(final String field, final Date value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            final Calendar calendar = new GregorianCalendar();
            calendar.setTime(value);
            criteria.add(Restrictions.le(fieldLast, calendar.getTime()));
        }
    }

    public void addLessOrEqOrNullDateElementAllowNullValue(final String field, final String property, final Map<String, Object> params) {
        if (params.get(property) != null) {
            addLessOrEqOrNullDateElement(field, (Date) params.get(property));
        } else {
            addIsNull(field);
        }
    }

    public void addLessOrEqOrNullDateElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addLessOrEqOrNullDateElement(field, (Date) params.get(property));
        }
    }

    public void addLessOrEqOrNullDateElement(final String field, final Date value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            final Calendar calendar = new GregorianCalendar();
            calendar.setTime(value);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            criteria.add(Restrictions.or(Restrictions.le(fieldLast, calendar.getTime()), Restrictions.isNull(fieldLast)));
        }
    }

    public void addDateBetweenElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addDateBetweenElement(field, params.get(property));
        }
    }

    public void addDateBetweenElement(final String field, final Object value) {
        if (value != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.eq(fieldLast, value));
        }
    }

    public void addDateBetween(Date date, String fieldBegin, String fieldEnd) {

        if (date != null) {
            String fieldLastBegin = createAliases(fieldBegin, criteria);
            String fieldLastEnd = createAliases(fieldEnd, criteria);

            criteria.add(Restrictions.and(
                    Restrictions.or(Restrictions.le(fieldLastBegin, date), Restrictions.isNull(fieldLastBegin)),
                    Restrictions.or(Restrictions.ge(fieldLastEnd, date), Restrictions.isNull(fieldLastEnd))));
        }

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInListElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addInListElement(field, (List) params.get(property));
        }
    }

    public void addInListElement(final String field, final List<Object> values) {
        if (values != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.in(fieldLast, values));
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInListOrNullElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addInListOrNullElement(field, (List) params.get(property));
        }
    }

    public void addInListOrNullElement(final String field, final List<Object> values) {
        if (values != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.or(Restrictions.in(fieldLast, values), Restrictions.isNull(fieldLast)));
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addNotInListElement(final String field, final String property, final Map<String, Object> params) {
        if (params.containsKey(property)) {
            addNotInListElement(field, (List) params.get(property));
        }
    }

    public void addOrderDesc(final String field) {
        if (StringUtils.isNotEmpty(field)) {
            criteria.addOrder(Order.desc(field));
        }
    }

    public void addOrderAsc(final String field) {
        if (StringUtils.isNotEmpty(field)) {
            criteria.addOrder(Order.asc(field));
        }
    }

    public void addNotInListElement(final String field, final List<Object> values) {
        if (values != null) {
            String fieldLast = createAliases(field, criteria);
            criteria.add(Restrictions.not(Restrictions.in(fieldLast, values)));
        }
    }

    public void addOrderBy(final String field, final Boolean desc) {
        if (field != null) {
            if (desc != null && desc) {
                criteria.addOrder(Order.desc(field));
            } else {
                criteria.addOrder(Order.asc(field));
            }
        }
    }

    private String createAliases(final String field, Criteria aliasCriteria) {
        String fieldLast = field;
        if (field.contains(".") && !"id".equals(field.split("\\.")[0])) {
            String[] fields = field.split("\\.");
            String alias = "";
            for (int i = 0; i < fields.length - 1; i++) {
                alias += (i != 0 ? "." + fields[i] : fields[i]);

                if (!aliasList.contains(fields[i]) && StringUtils.isNotBlank(fields[i]) && !"id".equals(fields[i])) {
                    aliasCriteria = aliasCriteria.createAlias(alias, fields[i]);
                    aliasList.add(fields[i]);
                }

                if (!"id".equals(fields[i])) {
                    fieldLast = fields[i] + "." + fields[i + 1];
                } else {
                    fieldLast = alias + "." + fields[i + 1];
                }
            }
            aliasCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        }
        return fieldLast;
    }
}
