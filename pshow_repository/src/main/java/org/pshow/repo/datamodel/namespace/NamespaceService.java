package org.pshow.repo.datamodel.namespace;

public interface NamespaceService extends NamespacePrefixResolver {

    static final String DATATYPE_NAMESAPCE_URI = "http://www.pshow.org/model/datatype/0.1";
    static final String SYSTEM_NAMESAPCE_URI   = "http://www.pshow.org/model/system/0.1";

    static final String DEFAULT_PREFIX         = "";

    public void registerNamespace(String prefix, String uri);

    public void unregisterNamespace(String prefix);
}
