package com.jivesoftware.managedbookmarks;

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
}
