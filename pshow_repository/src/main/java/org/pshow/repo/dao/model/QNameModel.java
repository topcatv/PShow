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
package org.pshow.repo.dao.model;

/**
 * @author roy
 * 
 */
public class QNameModel {

    private long   id;
    private long   namespaceId;
    private String namespaceURI;
    private String localName;

    public QNameModel(){
        
    }
    
    public QNameModel(String namespaceURI, String localName) {
        super();
        this.namespaceURI = namespaceURI;
        this.localName = localName;
    }

    public QNameModel(long namespaceId, String localName) {
        super();
        this.namespaceId = namespaceId;
        this.localName = localName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    
    public long getNamespaceId() {
        return namespaceId;
    }

    
    public void setNamespaceId(long namespaceId) {
        this.namespaceId = namespaceId;
    }

    
    public String getNamespaceURI() {
        return namespaceURI;
    }

    
    public void setNamespaceURI(String namespaceURI) {
        this.namespaceURI = namespaceURI;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((localName == null) ? 0 : localName.hashCode());
        result = prime * result + ((namespaceURI == null) ? 0 : namespaceURI.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QNameModel other = (QNameModel) obj;
        if (localName == null) {
            if (other.localName != null)
                return false;
        } else if (!localName.equals(other.localName))
            return false;
        if (namespaceURI == null) {
            if (other.namespaceURI != null)
                return false;
        } else if (!namespaceURI.equals(other.namespaceURI))
            return false;
        return true;
    }

}
