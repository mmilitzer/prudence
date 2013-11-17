/**
 * Copyright 2009-2013 Three Crickets LLC.
 * <p>
 * The contents of this file are subject to the terms of the LGPL version 3.0:
 * http://www.gnu.org/copyleft/lesser.html
 * <p>
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly from Three Crickets
 * at http://threecrickets.com/
 */

package com.threecrickets.prudence.internal.attributes;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.restlet.Context;
import org.restlet.resource.ServerResource;

/**
 * @author Tal Liron
 */
public class ResourceContextualAttributes extends NonVolatileContextualAttributes
{
	//
	// Construction
	//

	public ResourceContextualAttributes( ServerResource resource )
	{
		super( resource.getClass().getCanonicalName() );
		this.resource = resource;
	}

	//
	// Attributes
	//

	/**
	 * Pass-through documents can exist in {@link #getLibraryDocumentSources()}
	 * as well as in {@link #getDocumentSource()}.
	 * 
	 * @return The pass-through document names
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getPassThroughDocuments()
	{
		if( passThroughDocuments == null )
		{
			ConcurrentMap<String, Object> attributes = getAttributes();
			String key = prefix + ".passThroughDocuments";
			passThroughDocuments = (Set<String>) attributes.get( key );

			if( passThroughDocuments == null )
			{
				passThroughDocuments = new CopyOnWriteArraySet<String>();

				Set<String> existing = (Set<String>) attributes.putIfAbsent( key, passThroughDocuments );
				if( existing != null )
					passThroughDocuments = existing;
			}
		}

		return passThroughDocuments;
	}

	/**
	 * Whether or not to negotiate encoding by default. Defaults to true.
	 * <p>
	 * This setting can be configured by setting an attribute named
	 * <code>negotiateEncoding</code> in the application's {@link Context}.
	 * 
	 * @return Whether to allow content negotiation
	 */
	public boolean isNegotiateEncoding()
	{
		if( negotiateEncoding == null )
		{
			negotiateEncoding = (Boolean) getAttributes().get( prefix + ".negotiateEncoding" );

			if( negotiateEncoding == null )
				negotiateEncoding = true;
		}

		return negotiateEncoding;
	}

	/**
	 * The size in bytes beyond which responses could be encoded. Defaults to
	 * 1024.
	 * <p>
	 * This setting can be configured by setting an attribute named
	 * <code>encodeSizeThreshold</code> in the application's {@link Context}.
	 * 
	 * @return Whether to allow content negotiation
	 */
	public int getEncodeSizeThreshold()
	{
		if( encodeSizeThreshold == null )
		{
			Number number = (Number) getAttributes().get( prefix + ".encodeSizeThreshold" );

			if( number != null )
				encodeSizeThreshold = number.intValue();

			if( encodeSizeThreshold == null )
				encodeSizeThreshold = 1024;
		}

		return encodeSizeThreshold;
	}

	/**
	 * The default cache key pattern to use if the executable doesn't specify
	 * one. Defaults to "{ri}|{dn}".
	 * <p>
	 * This setting can be configured by setting an attribute named
	 * <code>defaultCacheKeyPattern</code> in the application's {@link Context}.
	 * 
	 * @return The default cache key
	 */
	public String getDefaultCacheKeyPattern()
	{
		if( defaultCacheKeyPattern == null )
		{
			defaultCacheKeyPattern = (String) getAttributes().get( prefix + ".defaultCacheKeyPattern" );

			if( defaultCacheKeyPattern == null )
				defaultCacheKeyPattern = "{ri}|{dn}";
		}

		return defaultCacheKeyPattern;
	}

	/**
	 * The cache key pattern handlers.
	 * <p>
	 * This setting can be configured by setting an attribute named
	 * <code>cacheKeyPatternHandlers</code> in the application's {@link Context}.
	 * 
	 * @return The cache key pattern handlers or null
	 */
	@SuppressWarnings("unchecked")
	public ConcurrentMap<String, String> getCacheKeyPatternHandlers()
	{
		if( cacheKeyPatternHandlers == null )
			cacheKeyPatternHandlers = (ConcurrentMap<String, String>) getAttributes().get( prefix + ".cacheKeyPatternHandlers" );

		return cacheKeyPatternHandlers;
	}

	//
	// ContextualAttributes
	//

	@Override
	public ConcurrentMap<String, Object> getAttributes()
	{
		if( attributes == null )
			attributes = resource.getContext().getAttributes();

		return attributes;
	}

	// //////////////////////////////////////////////////////////////////////////
	// Protected

	/**
	 * The resource.
	 */
	protected final ServerResource resource;

	// //////////////////////////////////////////////////////////////////////////
	// Private

	/**
	 * Pass-through documents can exist in
	 * {@link GeneratedTextResourceAttributes#getLibraryDocumentSources()} as
	 * well as in {@link GeneratedTextResourceAttributes#getDocumentSource()}.
	 */
	private Set<String> passThroughDocuments;

	/**
	 * Whether or not to negotiate encoding by default.
	 */
	private Boolean negotiateEncoding;

	/**
	 * The size in bytes beyond which responses could be encoded.
	 */
	private Integer encodeSizeThreshold;

	/**
	 * The default cache key pattern to use if the executable doesn't specify
	 * one.
	 */
	private String defaultCacheKeyPattern;

	/**
	 * The cache key pattern handlers.
	 */
	private ConcurrentMap<String, String> cacheKeyPatternHandlers;

	/**
	 * The attributes.
	 */
	private ConcurrentMap<String, Object> attributes;
}
