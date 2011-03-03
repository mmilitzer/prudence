//
// Prudence Guide Settings
//

document.execute('/defaults/application/settings/')

applicationName = 'Prudence Test'
applicationDescription = 'Prudence tests'
applicationAuthor = 'Tal Liron'
applicationOwner = 'Three Crickets'
applicationHomeURL = 'http://threecrickets.com/prudence/'
applicationContactEmail = 'prudence@threecrickets.com'

hosts = [[component.defaultHost, null], [mysiteHost, null]]

showDebugOnError = true

preheatResources = ['/data/jython/', '/data/jruby/', '/data/groovy/', '/data/clojure/', '/data/quercus/', '/data/rhino/']
