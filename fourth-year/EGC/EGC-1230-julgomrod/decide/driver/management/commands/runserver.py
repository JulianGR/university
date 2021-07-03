import os
import subprocess
import atexit
import signal
from optparse import make_option
from base.mods import register_status
from django.core.management.base import BaseCommand, CommandError
from django.core.management.commands.runserver import BaseRunserverCommand


class Command(BaseRunserverCommand):
    def inner_run(self, *args, **options):
        register_status("RUNSERVER")
        super(Command, self).inner_run(*args, **options)


