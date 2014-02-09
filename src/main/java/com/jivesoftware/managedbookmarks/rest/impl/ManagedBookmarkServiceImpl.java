package com.jivesoftware.managedbookmarks.rest.impl;

import com.jivesoftware.managedbookmarks.impl.ManagedBookmarkManagerImpl;
import com.jivesoftware.managedbookmarks.impl.ManagedBookmarkImpl;
import com.jivesoftware.managedbookmarks.rest.ManagedBookmarkService;
import com.jivesoftware.managedbookmarks.ManagedBookmarkManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * A builder for bookmark beans.
 *
 * @since Jive SBS 5.0
 */
public class ManagedBookmarkServiceImpl implements ManagedBookmarkService {
    static Logger log = LogManager.getLogger(ManagedBookmarkServiceImpl.class);


    private ManagedBookmarkManager managedBookmarkManager;

    @Override
    public void createBookmark(String bookmarkName) {
        log.info("ManageBookmarkService - createBookmark(" + bookmarkName + ")");
        //System.out.println("ManageBookmarkService - createBookmark");
        managedBookmarkManager.createManagedBookmark(bookmarkName);
    }

    // required to work with it's bean in spring.xml
    public void setManagedBookmarkManager(ManagedBookmarkManager managedBookmarkManager) {
        log.info("ManageBookmarkService - setManagedBookmarkManager;");
        //System.out.println("ManageBookmarkService - setManagedBookmarkManager");
        this.managedBookmarkManager = managedBookmarkManager;
    }

}
