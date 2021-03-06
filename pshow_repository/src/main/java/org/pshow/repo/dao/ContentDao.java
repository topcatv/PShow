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
package org.pshow.repo.dao;

import java.util.List;

import org.pshow.repo.dao.model.QNameModel;
import org.pshow.repo.datamodel.content.Content;


/**
 * @author roy
 *
 */
public interface ContentDao {

    String getUuidById(long id);

    void insertContent(Content cdata);

    void updateContent(Content cdata);

    Content getContentByUUID(String uuid);

    List<Content> getContentByParentUUID(String uuid);

    List<QNameModel> getFacetsByContent(String uuid);

    void removeFacetByContent(String id, long id2);

    void insertContentFacet(long contentId, long qnameId);

    Content getContentByID(long versionContentId);

}
