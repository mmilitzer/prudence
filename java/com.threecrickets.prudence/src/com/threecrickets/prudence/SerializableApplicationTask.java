/**
 * Copyright 2009-2011 Three Crickets LLC.
 * <p>
 * The contents of this file are subject to the terms of the LGPL version 3.0:
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * <p>
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly from Three Crickets
 * at http://threecrickets.com/
 */

package com.threecrickets.prudence;

import java.io.Serializable;

import org.restlet.Application;

import com.threecrickets.prudence.util.InstanceUtil;

/**
 * A serializable wrapper for an {@link ApplicationTask}.
 * 
 * @author Tal Liron
 * @see ApplicationTask
 * @see InstanceUtil#getApplication(String)
 */
public class SerializableApplicationTask implements Runnable, Serializable
{
	//
	// Construction
	//

	/**
	 * Constructor.
	 * 
	 * @param applicationName
	 *        The full name of the Restlet application in which this task will
	 *        execute
	 * @param documentName
	 *        The document name
	 * @param context
	 *        The context made available to the task
	 */
	public SerializableApplicationTask( String applicationName, String documentName, Object context )
	{
		this.applicationName = applicationName;
		this.documentName = documentName;
		this.context = context;
	}

	//
	// Attributes
	//

	/**
	 * Gets the application task.
	 * 
	 * @return The application task or null if the application was not found
	 * @see InstanceUtil#getApplication(String)
	 */
	public ApplicationTask getApplicationTask()
	{
		if( applicationTask == null )
		{
			Application application = InstanceUtil.getApplication( applicationName );
			if( application != null )
				applicationTask = new ApplicationTask( application, documentName, context );
		}

		return applicationTask;
	}

	//
	// Runnable
	//

	public void run()
	{
		ApplicationTask applicationTask = getApplicationTask();
		if( applicationTask == null )
			throw new RuntimeException( "Could not find an application named: " + applicationName );

		applicationTask.run();
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private static final long serialVersionUID = 1L;

	/**
	 * The full name of the Restlet application in which this task will execute.
	 */
	private String applicationName;

	/**
	 * The document name.
	 */
	private String documentName;

	/**
	 * The context made available to the task.
	 */
	private Object context;

	/**
	 * Cache for the generated application task.
	 */
	private transient ApplicationTask applicationTask;
}