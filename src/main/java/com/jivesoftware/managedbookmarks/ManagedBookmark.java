package com.jivesoftware.managedbookmarks;

/**
 * A managed bookmark represents a managed-ed bookmark product. Managed-ed bookmark are properties of a case.
 */
public interface ManagedBookmark {
    long getBookmarkID();

    void setBookmarkID(long id);

    String getBookmarkName();

    void setBookmarkName(String prodName);
}
