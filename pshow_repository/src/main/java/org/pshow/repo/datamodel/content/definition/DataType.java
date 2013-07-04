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

import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
            } else if (o instanceof InputStream) {
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

        public static Type valueOf(int value) {
            switch (value) {
                case 0:
                    return ANY;
                case 1:
                    return TEXT;
                case 2:
                    return CONTENT;
                case 3:
                    return INT;
                case 4:
                    return LONG;
                case 5:
                    return FLOAT;
                case 6:
                    return DOUBLE;
                case 7:
                    return DATE;
                case 8:
                    return DATETIME;
                case 9:
                    return BOOLEAN;
                default:
                    return null;
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

    public Type getType() throws DataTypeUnSupportExeception {
        try {
            Class<?> typeClass = Class.forName(javaClassName);
            if (typeClass.equals(Boolean.class)) {
                return Type.BOOLEAN;
            } else if (typeClass.equals(String.class)) {
                return Type.TEXT;
            } else if (typeClass.equals(InputStream.class)) {
                return Type.CONTENT;
            } else if (typeClass.equals(Integer.class)) {
                return Type.INT;
            } else if (typeClass.equals(Long.class)) {
                return Type.LONG;
            } else if (typeClass.equals(Float.class)) {
                return Type.FLOAT;
            } else if (typeClass.equals(Double.class)) {
                return Type.DOUBLE;
            } else if (typeClass.equals(Date.class)) {
                return Type.DATE;
            } else if (typeClass.equals(Calendar.class)) {
                return Type.DATETIME;
            } else if (typeClass.equals(Serializable.class)) {
                return Type.ANY;
            } else {
                throw new DataTypeUnSupportExeception(String.format("Unsupport [%s] data type", javaClassName));
            }
        } catch (ClassNotFoundException e) {
            throw new DataTypeUnSupportExeception(String.format("Unsupport [%s] data type", javaClassName), e);
        }
    }
}
