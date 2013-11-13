
app.settings = {
	description: {
		name: 'Prudence Example',
		description: 'The example application for the Prudence skeleton',
		author: 'Three Crickets',
		owner: 'Prudence'
	},

	errors: {
		debug: true, // TODO: problems with false
		homeUrl: 'http://threecrickets.com/prudence/', // Only used when debug=false
		contactEmail: 'info@threecrickets.com' // Only used when debug=false
	},
	
	code: {
		libraries: ['libraries'], // Handlers and tasks will be found here
		defrost: true,
		minimumTimeBetweenValidityChecks: '1s',
		defaultDocumentName: 'default',
		defaultExtension: 'js',
		defaultLanguageTag: 'javascript',
		sourceViewable: true
	},
	
	compression: {
		sizeThreshold: '1kb',
		exclude: []
	},
	
	uploads: {
		root: 'uploads',
		sizeThreshold: '0kb'
	},
	
	mediaTypes: {
		php: 'text/html'
	}
}
