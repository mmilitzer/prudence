#
# Stickstick Routing
#

$executable.container.include 'defaults/application/routing/'

$router.capture fix_url($resources_base_url + '/data/note/{id}/'), '/data/note/'