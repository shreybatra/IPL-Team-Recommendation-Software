import  requests
from bs4 import BeautifulSoup
import urllib.request



def trade_spider():
   url = 'http://www.espncricinfo.com/ci/content/player/index.html'
   source_code = requests.get(url)
   plain_text = source_code.text
   soup = BeautifulSoup(plain_text,"html.parser")
   for link in soup.findAll('li',{'class':['ctrytab','selectedtab']}):
       href = 'http://www.espncricinfo.com/ci/content/player/'+ link.contents[0].get('href')
       #print(link.string)
       #print("\n")
      # print(href)
       open2(href)
       

def open2(url):
    source_code = requests.get(url)
    plain_text = source_code.text
    ss =BeautifulSoup(plain_text,"html.parser")
    for link in ss.findAll('a',{'class':'ciHomeToolsLink'}):
        if link.string == 'Twenty20 International':
            #print("T20 INTERNATIONAL")
            href = 'http://www.espncricinfo.com' + link.get('href')
            open_page(href)

def open_page(url):
    source_code = requests.get(url)
    plain_text = source_code.text
    ss = BeautifulSoup(plain_text,"html.parser")
    for link in ss.findAll('li',{'class':'ciPlayername'}):
        #print(link.string)
        href = 'http://www.espncricinfo.com' + link.contents[0].get('href')
        #print(href)
        varglobal = href
        get_changed(href,varglobal)

def get_changed(item_url,varglobal):
    varglobal = varglobal.replace("content","engine")
    varglobal = varglobal.replace("www","stats")

    source_code = requests.get(item_url)
    plain_text = source_code.text
    ss = BeautifulSoup(plain_text,"html.parser")

    file = open("final.txt","a")


    k = 0;
    for name in ss.findAll('h1'):
        #print(name.contents[0].string)
        k +=1
        if k == 2:
            print(name.contents[0].string)
            file.write(name.contents[0].string)
            file.write("\n")

    
    print("Batting")
    file.write("Batting")
    file.write("\n")
    file.close()
    nextfunction(item_url,varglobal)
    file = open("final.txt","a")
    print("Bowling")
    file.write("Bowling")
    file.write("\n")
    file.close()
    bowling(item_url,varglobal)
    #file.close()

def nextfunction(item_url,varglobal):
    source_code = requests.get(item_url)
    plain_text = source_code.text
    ss = BeautifulSoup(plain_text,"html.parser")
    href= varglobal +'?class=3;host=6;template=results;type=batting;view=innings'
    #print(href)
    ppp(href)

def bowling(item_url,varglobal):
    source_code = requests.get(item_url)
    plain_text = source_code.text
    ss = BeautifulSoup(plain_text,"html.parser")
    href= varglobal +'?class=3;host=6;template=results;type=bowling;view=innings'
    #print(href)
    #print("Bowling Data:-")
    ppp(href)    

def ppp(url):
   source_code = requests.get(url)
   plain_text = source_code.text
   ss = BeautifulSoup(plain_text,"html.parser")

   file = open("final.txt","a")

   for link in ss.findAll('tr',{'class':'data1'}):
      abc = ""
      for aa in link.findAll('td'):
         if aa.text != "":
            abc = abc + aa.text + " "
      print(abc)
      file.write(abc)
      file.write("\n")

   file.close()


trade_spider()

