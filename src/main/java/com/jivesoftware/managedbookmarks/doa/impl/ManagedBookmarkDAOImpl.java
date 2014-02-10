package com.jivesoftware.managedbookmarks.doa.impl;

import com.jivesoftware.base.database.dao.DAOException;
import com.jivesoftware.base.database.dao.JiveJdbcDaoSupport;
import com.jivesoftware.base.database.dao.SequenceProvider;
import com.jivesoftware.managedbookmarks.doa.ManagedBookmarkDAO;
import com.jivesoftware.managedbookmarks.impl.ManagedBookmarkImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ManagedBookmarkDAOImpl extends JiveJdbcDaoSupport implements ManagedBookmarkDAO{

    protected static final Logger log = LogManager.getLogger(ManagedBookmarkDAOImpl.class.getName());

    private static final String BOOKMARK_ID_FIELD = "bookmarkID";
    private static final String BOOKMARK_NAME_FIELD = "bookmarkName";
    private static final String CREATE_MANAGED_BOOKMARK = "INSERT into managedBookmark (bookmarkID, bookmarkName) " +
            "VALUES (:bookmarkID, :bookmarkName)";
    private static final String GET_MANAGED_BOOKMARKS = "SELECT bookmarkID, bookmarkName FROM managedBookmark";


    private SequenceProvider sequenceProvider;

    public void setSequenceProvider(SequenceProvider sequenceProvider) {
        log.info("ManagedBookmarkDAOImpl - setSequenceProvider;");
        this.sequenceProvider = sequenceProvider;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ManagedBookmarkImpl createManagedBookmark(ManagedBookmarkImpl managedBookmark) {
        log.info("ManagedBookmarkDAOImpl - ManagedBookmarkImpl (managedBookmark[" + managedBookmark.getBookmarkName() + ", " + managedBookmark.getBookmarkID() +"])");
        //System.out.println("ManagedBookmarkDAOImpl - ManagedBookmarkImpl");
        try {
            long id = sequenceProvider.nextId();
            managedBookmark.setBookmarkID(id);
            template().update(CREATE_MANAGED_BOOKMARK, source(managedBookmark));

        } catch (DataAccessException e) {
            log.error("Error creating new Managed Bookmark");
            log.error(e);
            throw new DAOException(e);
        }
        return managedBookmark;
    }

    public List<ManagedBookmarkImpl> getManagedBookmarks() {
        log.info("ManagedBookmarkDAOImpl - getManagedBookmarks");
        return template().query(GET_MANAGED_BOOKMARKS, rm());
    }

    private ManagedBookmarkRowMapper rm() {
        log.info("ManagedBookmarkDAOImpl - ManagedBookmarkRowMapper");
        return new ManagedBookmarkRowMapper();
    }

    private static class ManagedBookmarkRowMapper implements RowMapper<ManagedBookmarkImpl> {
        public ManagedBookmarkImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
            log.info("ManagedBookmarkDAOImpl/ManagedBookmarkRowMapper - mapRow");
            ManagedBookmarkImpl managedBookmark = new ManagedBookmarkImpl();
            managedBookmark.setBookmarkID(rs.getLong(BOOKMARK_ID_FIELD));
            managedBookmark.setBookmarkName(rs.getString(BOOKMARK_NAME_FIELD));
            return managedBookmark;
        }
    }
}
