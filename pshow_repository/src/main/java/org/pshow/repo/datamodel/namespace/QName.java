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
package org.pshow.repo.datamodel.namespace;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * @author roy
 * 
 */
public final class QName implements Comparable<QName>, Serializable, Cloneable {

    private static final long serialVersionUID = 2963846018008835569L;
    private final String      prefix;
    private final String      namespaceURI;
    private final String      localName;

    private QName(String uri, String localName, String prefix) {
        this.prefix = prefix;
        this.namespaceURI = uri;
        this.localName = localName;
    }

    public static QName createQName(String uri, String localName) {
        if (localName == null || localName.length() == 0) { throw new InvalidQNameException("A QName must consist of a local name"); }
        if (uri == null || uri.length() == 0) { throw new InvalidQNameException("A QName must consist of a namespace uri"); }
        return new QName(uri, localName, null);
    }

    public static QName createQName(String prefix, String localName, NamespacePrefixResolver prefixResolver) throws InvalidQNameException, NamespaceException {
        // Validate Arguments
        if (localName == null || localName.length() == 0) { throw new InvalidQNameException("A QName must consist of a local name"); }
        if (prefixResolver == null) { throw new IllegalArgumentException("A Prefix Resolver must be specified"); }
        if (prefix == null) {
            prefix = NamespaceService.DEFAULT_PREFIX;
        }

        String uri = prefixResolver.getNamespaceURI(prefix);
        if (uri == null) { throw new NamespaceException("Namespace prefix " + prefix + " is not mapped to a namespace URI"); }
        return new QName(uri, localName, prefix);
    }

    public static String resolvePrefix(String name) {
        if (StringUtils.isNotBlank(name)) {
            if (StringUtils.contains(name, ":")) {
                String[] split = StringUtils.split(name, ":");
                return split[0];
            }
        }
        return "";
    }

    public static String resolveLocalName(String name) {
        if (StringUtils.isNotBlank(name)) {
            if (StringUtils.contains(name, ":")) {
                String[] split = StringUtils.split(name, ":");
                return split[1];
            }
        }
        return "";
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNamespaceURI() {
        return namespaceURI;
    }

    public String getLocalName() {
        return localName;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((localName == null) ? 0 : localName.hashCode());
        result = prime * result + ((namespaceURI == null) ? 0 : namespaceURI.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        QName other = (QName) obj;
        if (localName == null) {
            if (other.localName != null) return false;
        } else if (!localName.equals(other.localName)) return false;
        if (namespaceURI == null) {
            if (other.namespaceURI != null) return false;
        } else if (!namespaceURI.equals(other.namespaceURI)) return false;
        return true;
    }

    public int compareTo(QName qname) {
        int namespaceComparison = this.namespaceURI.compareTo(qname.namespaceURI);
        if (namespaceComparison != 0) { return namespaceComparison; }
        return this.localName.compareTo(qname.localName);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "QName [prefix=" + prefix + ", namespaceURI=" + namespaceURI + ", localName=" + localName + "]";
    }
}
