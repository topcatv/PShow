/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pshow.repo.datamodel.content;

import java.io.InputStream;
import java.io.Serializable;

import org.pshow.repo.util.MimetypeMap;

/**
 * @author roy
 * 
 */
public class ContentData implements Serializable {

    private static final long serialVersionUID          = 8979634213050121462L;

    private static char[]     INVALID_CONTENT_URL_CHARS = new char[] { '|' };

    private String      contentUrl;
    private String      mimetype;
    private long        size;
    private String      name;
    private InputStream data;

    /**
     * Helper method to determine if the data represents any physical content or
     * not.
     * 
     * @param contentData
     *            the content to check (may be <tt>null</tt>)
     * @return <tt>true</tt> if the value is non-null
     */
    public static boolean hasContent(ContentData contentData) {
        if (contentData == null) {
            return false;
        }
        return contentData.contentUrl != null;
    }

    /**
     * Create a compound set of data representing a single instance of
     * <i>content</i>.
     * <p>
     * In order to ensure data integrity, the {@link #getMimetype() mimetype}
     * must be set if the {@link #getContentUrl() content URL} is set.
     * 
     * @param contentUrl
     *            the content URL. If this value is non-null, then the
     *            <b>mimetype</b> must be supplied.
     * @param mimetype
     *            the content mimetype. This is mandatory if the
     *            <b>contentUrl</b> is specified.
     * @param size
     *            the content size.
     * @param encoding
     *            the content encoding. This is mandatory if the
     *            <b>contentUrl</b> is specified.
     */
    public ContentData(String contentUrl, String mimetype, long size,
            String name, InputStream data) {
        if (contentUrl != null && (mimetype == null || mimetype.length() == 0)) {
            mimetype = MimetypeMap.MIMETYPE_BINARY;
        }
        checkContentUrl(contentUrl, mimetype);
        this.contentUrl = contentUrl;
        this.mimetype = mimetype;
        this.size = size;
        this.name = name;
        this.data = data;
    }

    /**
     * @return Returns a string of form:
     *         <code>contentUrl=xxx|mimetype=xxx|size=xxx|encoding=xxx|locale=xxx</code>
     */
    public String toString() {
        return getInfoUrl();
    }

    /**
     * @return Returns a URL containing information on the content including the
     *         mimetype, locale, encoding and size, the string is returned in
     *         the form:
     *         <code>contentUrl=xxx|mimetype=xxx|size=xxx|encoding=xxx|locale=xxx</code>
     */
    public String getInfoUrl() {
        StringBuilder sb = new StringBuilder(80);
        sb.append("contentUrl=").append(contentUrl == null ? "" : contentUrl)
                .append("|mimetype=").append(mimetype == null ? "" : mimetype)
                .append("|size=").append(size).append("|encoding=")
                .append(name == null ? "" : name).append("|locale=");
        return sb.toString();
    }

    /**
     * @return Returns a URL identifying the specific location of the content.
     *         The URL must identify, within the context of the originating
     *         content store, the exact location of the content.
     * @throws ContentIOException
     */
    public String getContentUrl() {
        return contentUrl;
    }

    /**
     * Checks that the content URL is correct, and also that the mimetype is
     * non-null if the URL is present.
     * 
     * @param contentUrl
     *            the content URL to check
     * @param mimetype
     *            the encoding must be present if the content URL is present
     * @param encoding
     *            the encoding must be valid and present if the content URL is
     *            present
     */
    private void checkContentUrl(String contentUrl, String mimetype) {
        // check the URL
        if (contentUrl != null && contentUrl.length() > 0) {
            for (int i = 0; i < INVALID_CONTENT_URL_CHARS.length; i++) {
                for (int j = contentUrl.length() - 1; j > -1; j--) {
                    if (contentUrl.charAt(j) == INVALID_CONTENT_URL_CHARS[i]) {
                        throw new IllegalArgumentException(
                                "The content URL contains an invalid char: \n"
                                        + "   content URL: " + contentUrl
                                        + "\n" + "   char: "
                                        + INVALID_CONTENT_URL_CHARS[i] + "\n"
                                        + "   position: " + j);
                    }
                }
            }

            // check that mimetype is present if URL is present
            if (mimetype == null) {
                throw new IllegalArgumentException(
                        "\n"
                                + "The content mimetype must be set whenever the URL is set: \n"
                                + "   content URL: " + contentUrl + "\n"
                                + "   mimetype: " + mimetype);
            }
        }
    }

    /**
     * Gets content's mimetype.
     * 
     * @return Returns a standard mimetype for the content or null if the
     *         mimetype is unkown
     */
    public String getMimetype() {
        return mimetype;
    }

    /**
     * Get the content's size
     * 
     * @return Returns the size of the content
     */
    public long getSize() {
        return size;
    }

    /**
     * Gets the content's encoding.
     * 
     * @return Returns a valid Java encoding, typically a character encoding, or
     *         null if the encoding is unkown
     */
    public String getEncoding() {
        return name;
    }

    /**
     * @return hashCode
     */
    @Override
    public int hashCode() {
        if (contentUrl != null) {
            return contentUrl.hashCode();
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContentData other = (ContentData) obj;
        if (contentUrl == null) {
            if (other.contentUrl != null)
                return false;
        } else if (!contentUrl.equals(other.contentUrl))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (mimetype == null) {
            if (other.mimetype != null)
                return false;
        } else if (!mimetype.equals(other.mimetype))
            return false;
        if (size != other.size)
            return false;
        return true;
    }

    
    public InputStream getData() {
        return data;
    }

    
    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    
    public void setSize(long size) {
        this.size = size;
    }

    
    public void setEncoding(String encoding) {
        this.name = encoding;
    }
}
