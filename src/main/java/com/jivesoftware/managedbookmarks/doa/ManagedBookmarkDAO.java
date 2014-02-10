package com.jivesoftware.managedbookmarks.doa;

import com.jivesoftware.managedbookmarks.impl.ManagedBookmarkImpl;

import java.util.List;

/**
 * This DAO provides methods to directly manipulate managedBookmark table. ManagedBookmarkManager should always be used
 * to modify managedBookmark data and maintain valid caches.
 */
public interface ManagedBookmarkDAO {
    /**
     * Add a new managedBookmark to the table. Should never be called directly, but go through the ManagedBookmarkManager.
     * @param managedBookmark object to be persisted
     * @return newly created ManagedBookmark
     */
    ManagedBookmarkImpl createManagedBookmark(ManagedBookmarkImpl managedBookmark);

    /**
     * Return the list of available managed bookmarks. Should only be retrieved by the manager and persisted to cache.
     * @return
     */
    List<ManagedBookmarkImpl>  getManagedBookmarks();
}