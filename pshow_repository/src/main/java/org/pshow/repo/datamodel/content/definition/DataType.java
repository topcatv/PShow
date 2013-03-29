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
package org.pshow.repo.datamodel.content.definition;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.pshow.repo.datamodel.content.ContentData;

/**
 * @author roy
 * 
 */
public class DataType {

    private String name          = null;
    private String title         = null;
    private String description   = null;
    private String javaClassName = null;

    public static enum Type {
        ANY(0), TEXT(1), CONTENT(2), INT(3), LONG(4), FLOAT(5), DOUBLE(6), DATE(7), DATETIME(8), BOOLEAN(9);

        private final int type;

        Type(int type) {
            this.type = type;
        }

        public int getActualType() {
            return this.type;
        }

        public static Type getObjectType(Serializable o) throws DataTypeUnSupportExeception {
            if (o instanceof Boolean) {
                return BOOLEAN;
            } else if (o instanceof String) {
                return TEXT;
            } else if (o instanceof ContentData) {
                return CONTENT;
            } else if (o instanceof Integer) {
                return INT;
            } else if (o instanceof Long) {
                return LONG;
            } else if (o instanceof Float) {
                return FLOAT;
            } else if (o instanceof Double) {
                return DOUBLE;
            } else if (o instanceof Date) {
                return DATE;
            } else if (o instanceof Calendar) {
                return DATETIME;
            } else if (o instanceof Serializable) {
                return ANY;
            } else {
                throw new DataTypeUnSupportExeception(String.format("Unsupport [%s] data type", o.getClass().getName()));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }
}
