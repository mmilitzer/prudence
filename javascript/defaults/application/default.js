//
// Prudence Application
//

importClass(
	org.restlet.data.Reference,
	org.restlet.data.MediaType,
	com.threecrickets.scripturian.file.DocumentFileSource,
	com.threecrickets.prudence.util.DelegatedStatusService);

//
// Settings
//

includeOrDefault(applicationBasePath + '/settings', 'defaults/application/settings');

//
// Application
//

includeOrDefault(applicationBasePath + '/application', 'defaults/application/application');

application.name = applicationName;
application.description = applicationDescription;
application.author = applicationAuthor;
application.owner = applicationOwner;

var attributes = application.context.attributes;

//
// StatusService
//

application.statusService = new DelegatedStatusService();
application.statusService.debugging = showDebugOnError;
application.statusService.homeRef = new Reference(applicationHomeURL);
application.statusService.contactEmail = applicationContactEmail;

//
// MetaData
//

application.metadataService.addExtension('php', MediaType.TEXT_HTML);

//
// Routing
//

includeOrDefault(applicationBasePath + '/routing', 'defaults/application/routing');

//
// Logging
//

application.context.setLogger(applicationLoggerName);

//
// Additional/Override Runtime Attributes
//

for(var key in runtimeAttributes) {
	attributes.put(key, runtimeAttributes[key]);
}
