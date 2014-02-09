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

public class ManagedBookmarkDAOImpl extends JiveJdbcDaoSupport implements ManagedBookmarkDAO{

    protected static final Logger log = LogManager.getLogger(ManagedBookmarkDAOImpl.class.getName());

    private static final String PRODUCT_ID_FIELD = "bookmarkID";
    private static final String PRODUCT_NAME_FIELD = "bookmarkName";
    private static final String CREATE_SUPPORT_PRODUCT = "INSERT into supportProduct (productID, productName) " +
            "VALUES (:productID, :productName)";
    private static final String CREATE_MANAGED_BOOKMARK = "INSERT into managedBookmark (bookmarkID, bookmarkName) " +
            "VALUES (:bookmarkID, :bookmarkName)";

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
}
