package org.pshow.repo.datamodel.namespace;

public interface NamespaceService extends NamespacePrefixResolver {

	static final String	DEFAULT_PREFIX	= "";

	public void registerNamespace(String prefix, String uri);

	public void unregisterNamespace(String prefix);
}
