/**
 * Copyright 2009-2017 Three Crickets LLC.
 * <p>
 * The contents of this file are subject to the terms of the LGPL version 3.0:
 * http://www.gnu.org/copyleft/lesser.html
 * <p>
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly from Three Crickets
 * at http://threecrickets.com/
 */

package com.threecrickets.prudence.service;

import com.threecrickets.prudence.DelegatedResource;
import com.threecrickets.prudence.internal.CachingUtil;
import com.threecrickets.prudence.internal.attributes.DelegatedResourceAttributes;
import com.threecrickets.scripturian.Executable;
import com.threecrickets.scripturian.document.DocumentDescriptor;

/**
 * Document service exposed to executables.
 * 
 * @author Tal Liron
 * @see DelegatedResource
 */
public class DelegatedResourceDocumentService extends ResourceDocumentServiceBase<DelegatedResource, DelegatedResourceAttributes, DelegatedResourceConversationService>
{
	//
	// Construction
	//

	/**
	 * Constructor.
	 * 
	 * @param resource
	 *        The resource
	 * @param documentDescriptor
	 *        The initial document descriptor
	 * @param conversationService
	 *        The conversation service
	 * @param cachingUtil
	 *        The caching utilities
	 */
	public DelegatedResourceDocumentService( DelegatedResource resource, DocumentDescriptor<Executable> documentDescriptor, DelegatedResourceConversationService conversationService,
		CachingUtil<DelegatedResource, DelegatedResourceAttributes> cachingUtil )
	{
		super( resource, resource.getAttributes(), conversationService, cachingUtil );
		pushDocumentDescriptor( documentDescriptor );
	}
}