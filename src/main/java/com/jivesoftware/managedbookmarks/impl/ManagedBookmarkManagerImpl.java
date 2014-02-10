package com.jivesoftware.managedbookmarks.impl;

import com.jivesoftware.community.JiveManager;
import com.jivesoftware.managedbookmarks.ManagedBookmark;
import com.jivesoftware.managedbookmarks.ManagedBookmarkManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.jivesoftware.managedbookmarks.doa.ManagedBookmarkDAO;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 2/1/14
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagedBookmarkManagerImpl implements ManagedBookmarkManager, JiveManager {

    private static final Logger log = LogManager.getLogger(ManagedBookmarkManagerImpl.class);

    private ManagedBookmarkDAO  managedBookmarkDAO;

    public void setManagedBookmarkDAO(ManagedBookmarkDAO managedBookmarkDAO) {
        log.info("ManagedBookmarkManagerImpl - setManagedBookmarkDAO;");
        //System.out.println("ManagedBookmarkManagerImpl - setManagedBookmarkDAO");
        this.managedBookmarkDAO = managedBookmarkDAO;
    }

    @Override
    public ManagedBookmark createManagedBookmark(String bookmarkName) {
        log.info("ManagedBookmarkManagerImpl - createManagedBookmark(" + bookmarkName +")");
        //System.out.println("ManagedBookmarkManagerImpl - createManagedBookmark");
        ManagedBookmarkImpl managedBookmark = new ManagedBookmarkImpl();
        managedBookmark.setBookmarkName(bookmarkName);

        managedBookmark = managedBookmarkDAO.createManagedBookmark(managedBookmark);

        return managedBookmark;
    }

    public List<ManagedBookmarkImpl> getManagedBookmarks() {
        log.info("ManagedBookmarkManagerImpl - getManagedBookmarks");
        return managedBookmarkDAO.getManagedBookmarks();
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
