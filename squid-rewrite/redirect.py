#!/usr/bin/env python3
import re
import sys
import logging
from datetime import datetime

logging.basicConfig(filename='/squid-redirect.log', level=logging.DEBUG)


def main():
    request = sys.stdin.readline()
    while request:
        # logging.debug(datetime.now().strftime('%Y-%m-%d %H:%M:%S') + ': ' + request + '\n')
        tmp = request.split()
        url = tmp[0]
        url = url.replace("org_id=xxxx", "org_id=yyyy")
        response = 'OK'
        response += ' rewrite-url='+url
        # response += ' status=302 url='+url
        response += '\n'
        # logging.debug(datetime.now().strftime('%Y-%m-%d %H:%M:%S') + ':> ' + response + '\n')
        sys.stdout.write(response)
        sys.stdout.flush()
        request = sys.stdin.readline()


if __name__ == '__main__':
    main()
