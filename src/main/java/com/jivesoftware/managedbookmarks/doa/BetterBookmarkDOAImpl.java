package com.jivesoftware.managedbookmarks.doa;

import com.google.common.collect.ImmutableMap;
import com.jivesoftware.base.database.dao.DAOException;
import com.jivesoftware.base.database.dao.JiveJdbcDaoSupport;
import com.jivesoftware.base.database.dao.SequenceProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 10/1/13
 * Time: 7:06 PM
 * To change this template use File | Settings | File Templates.
 */

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BetterBookmarkDOAImpl extends JiveJdbcDaoSupport implements BetterBookmarkDOA {

    //review queries - some values were removed
    public static final String INSERT_SQL = "INSERT INTO jiveBetterBookmark (id, markedType, markedId, "
            + "creatorId, subject, creationDate, modificationDate) "
            + "VALUES (:id, :markedType, :markedId, :creatorId, :subject, "
            + " :creationDate, :modificationDate)";

    public static final String GET_SQL = "SELECT id, markedType, markedId, creatorId, "
            + "subject, creationDate, modificationDate, (SELECT COUNT(*) "
            + "FROM jiveBookmark WHERE markedType = x.markedType AND jiveBookmark.markedId = x.markedId) "
            + "as bookmarkCount FROM jiveBetterBookmark x";

    public static final String GET_BY_ID_SQL = GET_SQL  + " WHERE id = :id" ;

    public static final String DELETE_SQL = "DELETE FROM jiveBetterBookmark";

    public static final String DELETE_BY_ID_SQL = DELETE_SQL + " WHERE id = :id";

    protected static final Logger log = LogManager.getLogger(BetterBookmarkDOAImpl.class);

    protected SequenceProvider sequenceProvider;
    @Required
    public final void setSequenceProvider(SequenceProvider sequenceProvider) {
        this.sequenceProvider = sequenceProvider;
    }

    @Transactional(readOnly = false, propagation = Propagation.MANDATORY)
    public long create(BetterBookmarkBean betterBookmarkBean) throws IllegalArgumentException, DAOException {
        SqlParameterSource parameterSource = prepare(betterBookmarkBean);
        template().update(INSERT_SQL, parameterSource);
        return (Long) parameterSource.getValue("id");
    }

    @Transactional(readOnly = false, propagation = Propagation.MANDATORY)
    public List<Long> create(Collection<BetterBookmarkBean> betterBookmarkBean) throws DAOException {
        SqlParameterSource[] creatableBookmarkBeans = prepare(betterBookmarkBean);
        template().batchUpdate(INSERT_SQL, creatableBookmarkBeans);
        List<Long> ids = new ArrayList<Long>();
        for (SqlParameterSource source : creatableBookmarkBeans) {
            ids.add((Long) source.getValue("id"));
        }
        return Collections.unmodifiableList(ids);
    }

    public BetterBookmarkBean getById(long id) {
        try {
            return template()
                    .queryForObject(GET_BY_ID_SQL, mapBetterBookmark(), mapSource(ImmutableMap.of("id", id)));
        }
        catch (EmptyResultDataAccessException erdae) {
            if (log.isDebugEnabled()) {
                log.debug(String.format("BetterBookmark '%d' could not be loaded", id), erdae);
            }
            return null;
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.MANDATORY)
    public boolean deleteById(long id) {
        return template().update(DELETE_BY_ID_SQL, ImmutableMap.of("id", id)) >= 1;
    }

    protected SqlParameterSource[] prepare(Collection<BetterBookmarkBean> betterBookmarkBeans) {
        SqlParameterSource[] sources = new SqlParameterSource[betterBookmarkBeans.size()];
        int i = 0;
        for (BetterBookmarkBean betterBookmarkBean : betterBookmarkBeans) {
            if (i >= sources.length) {
                throw new ConcurrentModificationException("BetterBookmark listing was modified while "
                        + "it was being persisted.");
            }
            sources[i++] = prepare(betterBookmarkBean);
        }
        // we will fail anyway if the collection was modified so we want to preempt the failure with a more useful
        // message.
        if (i != sources.length) {
            throw new ConcurrentModificationException("BetterBookmark listing was modified while it was being persisted.");
        }
        return sources;
    }

    protected SqlParameterSource prepare(BetterBookmarkBean betterBookmarkBean) {
        Date date = new Date();
        try {
            betterBookmarkBean = (BetterBookmarkBean) betterBookmarkBean.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw new RuntimeException(cnse);
        }
        if (betterBookmarkBean.getId() <= 0) {
            betterBookmarkBean.setId(sequenceProvider.nextId());
        }
        if (betterBookmarkBean.getCreationDate() == null) {
            betterBookmarkBean.setCreationDate(date);
        }
        betterBookmarkBean.setModificationDate(date);
        if (StringUtils.isBlank(betterBookmarkBean.getSubject())) {
            betterBookmarkBean.setSubject(null);
        }
        return source(betterBookmarkBean);
    }


    protected static ParameterizedRowMapper<BetterBookmarkBean> mapBetterBookmark() {
        return new BetterBookmarkBeanRowMapper(true);
    }

    private static class BetterBookmarkBeanRowMapper implements ParameterizedRowMapper<BetterBookmarkBean> {

        protected boolean includesCount = true;

        private BetterBookmarkBeanRowMapper(boolean includesCount) {
            this.includesCount = includesCount;
        }

        public BetterBookmarkBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            BetterBookmarkBean bean = new BetterBookmarkBean();
            bean.setId(rs.getLong("id"));
            bean.setMarkedType(rs.getInt("markedType"));
            bean.setMarkedId(rs.getLong("markedId"));
            bean.setCreatorId(rs.getLong("creatorId"));
            bean.setSubject(rs.getString("subject"));
            bean.setCreationDate(new Date(rs.getLong("creationDate")));
            bean.setModificationDate(new Date(rs.getLong("modificationDate")));

            return bean;
        }
    }


}
