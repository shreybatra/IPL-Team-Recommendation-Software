import requests
from bs4 import BeautifulSoup
import urllib.request

def trade_spider(max_pages):
    page = 1
    while page <= max_pages:
        url = 'http://www.espncricinfo.com/ci/content/player/index.html'
        source_code = requests.get(url)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text,"html.parser")
        for link in soup.findAll('li',{'class':['ctrytab','selectedtab']}):
            href = 'http://www.espncricinfo.com/ci/content/player/'+ link.contents[0].get('href')
            #print(link.string)
            #print(href)
            open2(href)
        page += 1


def open2(url):
    source_code = requests.get(url)
    plain_text = source_code.text
    ss =BeautifulSoup(plain_text,"html.parser")
    for link in ss.findAll('a',{'class':'ciHomeToolsLink'}):
        if link.string == 'Twenty20 International':
            href = 'http://www.espncricinfo.com' + link.get('href')
            open_page(href)

def open_page(url):
    source_code = requests.get(url)
    plain_text = source_code.text
    ss = BeautifulSoup(plain_text,"html.parser")
    for link in ss.findAll('li',{'class':'ciPlayername'}):
        print(link.string)
        href = 'http://www.espncricinfo.com' + link.contents[0].get('href')
        #print(href)
        get_single_item_data(href)


def get_single_item_data(item_url):
    source_code = requests.get(item_url)
    plain_text = source_code.text
    soup = BeautifulSoup(plain_text,"html.parser")
    abc = ""
    i = 0
    j = 0
    for item_name in soup.findAll('td'):
        
        if (item_name.string == 'First-class' or item_name.string == 'List A' or item_name.string == 'Twenty20') and i==1:
            print(abc)
            abc = ""
            j = 0
            break
        if j == 1:
            abc = abc + item_name.string + " "
            i = 1
        if item_name.string == 'T20Is':
            j = 1



trade_spider(1)
