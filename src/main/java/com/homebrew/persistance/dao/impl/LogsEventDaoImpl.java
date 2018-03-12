package com.homebrew.persistance.dao.impl;

import com.homebrew.persistance.dao.ILogsEventDao;
import com.homebrew.persistance.dto.LogsEventDto;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public class LogsEventDaoImpl extends AbstractDaoImpl<LogsEventDto, Integer> implements ILogsEventDao {

    private static final Logger LOGGER = Logger.getLogger(LogsEventDaoImpl.class);

    public LogsEventDaoImpl() {
        super(LogsEventDto.class);
    }

	@Override
	public void addSearchOrder(Criteria criteria) {
		criteria.addOrder(Order.desc(LogsEventDto.FIELD_ID));
	}
}
