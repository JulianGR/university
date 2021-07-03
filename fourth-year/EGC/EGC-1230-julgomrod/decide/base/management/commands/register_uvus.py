from django.core.management.base import BaseCommand, CommandError
from base.mods import register_status

class RegisterCommand(BaseCommand):
    help = 'Returns the ID fot a user'

    #def add_arguments(self, parser):
    #    parser.add_argument('uvus', nargs='+', type=str)

    def handle(self, *args, **options):
        #uvus=str(options['uvus'][0])

        register_status('REGISTERED')

