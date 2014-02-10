package com.jivesoftware.managedbookmarks;

import com.jivesoftware.managedbookmarks.impl.ManagedBookmarkImpl;

import java.util.List;

/**
 * This manager provides an interface to create managed bookmarks.
 */
public interface ManagedBookmarkManager {

    /**
     * Create a new ManagedBookmrark object.
     * @param bookmarkName name of new managed bookmark
     * @return newly created managed bookmark
     */
    ManagedBookmark createManagedBookmark(String bookmarkName);

    /**
     * Retrieve a list managed bookmarks.
     * @return list of Bookmarks
     */
    List<ManagedBookmarkImpl> getManagedBookmarks();
}
