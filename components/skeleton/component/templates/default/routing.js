
app.routes = {
	'/*': [
		'manual',
		'templates',
		{
			type: 'cacheControl',
			mediaTypes: {
				'image/*': '1m',
				'text/css': '1m',
				'application/x-javascript': '1m'
			},
			next: {
				type: 'less',
				next: 'static'
			}
		}
	],

	'/example1/': '@example', // see /libraries/dispatched/example.js
	'/example2/': '/example/'  // see /libraries/includes/example.html
}

app.hosts = {
	'default': '/${APPLICATION}/'
}
