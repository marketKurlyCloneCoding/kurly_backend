from selenium import webdriver
import pandas as pd
import time
import pymysql

# host_name="studydb.c8lf74vqhony.ap-northeast-2.rds.amazonaws.com"
# username="admin"
# password="password"
# database_name="market_kurly"
#
# dp=pymysql.connect(
#     host=host_name,
#     port=3306,
#     user=username,
#     passwd=password,
#     db=database_name,
#     charset='utf8'
# )


driver = webdriver.Chrome('chromedriver.exe')

driver.get('https://www.kurly.com/shop/goods/goods_list.php?category=907008')

time.sleep(3)


def crawling(end, category_id):
    for i in range(1, end + 1):

        img = driver.find_element_by_css_selector(
            f'#goodsList > div.list_goods > div > ul > li:nth-child({i}) > div > div > a > img').get_attribute('src')
        title = driver.find_element_by_css_selector(
            f'#goodsList > div.list_goods > div > ul > li:nth-child({i}) > div > a > span.name').text

        sub_title = driver.find_element_by_css_selector(
            f'#goodsList > div.list_goods > div > ul > li:nth-child({i}) > div > a > span.desc').text

        # 할인이 존재하는 경우 가격
        try:
            price = driver.find_element_by_css_selector(
                f'#goodsList > div.list_goods > div > ul > li:nth-child({i}) > div > a > span.cost > span.price').text[
                    :-1]
            price = price.replace(',', '')
            price = int(price)
        except:
            price = None

        # 할인이 존재하지 않을 때 가격
        if price == None:
            price = driver.find_element_by_css_selector(
                f'#goodsList > div.list_goods > div > ul > li:nth-child({i}) > div > a > span.cost > span').text[:-1]
            price = price.replace(',', '')

        try:
            dc = driver.find_element_by_css_selector(
                f'#goodsList > div.list_goods > div > ul > li:nth-child({i}) > div > a > span.cost > span.dc').text[:-1]
            dc = int(dc)
        except:
            dc = None

        try:
            original_price = driver.find_element_by_css_selector(
                f'#goodsList > div.list_goods > div > ul > li:nth-child({i}) > div > a > span.cost > span.original').text[
                             :-1]
            original_price = original_price.replace(',', '')
            original_price = int(original_price)

        except:
            original_price = None

        data.append([category_id, img, title, sub_title, price, dc, original_price])


# 채소 > 친환경 -> category_id=1
category_id = 1
data = []
crawling(99, category_id)

# 채소 > 고구마,감자, 당근 -> category_id=2
c = driver.find_element_by_css_selector('#lnbMenu > div.inner_lnb > ul > li:nth-child(3) > a')
c.click()
time.sleep(2)
category_id = 2
crawling(44, category_id)

# 시금치, 쌈채소, 나물 -> category_id=3
c = driver.find_element_by_css_selector('#lnbMenu > div.inner_lnb > ul > li:nth-child(4) > a')
c.click()
time.sleep(2)
category_id = 3
crawling(81, category_id)

# 과일,견과,쌀 > 친환경
driver.get('https://www.kurly.com/shop/goods/goods_list.php?category=908008')
time.sleep(3)

category_id = 4
crawling(44, category_id)

# 과일,견과,쌀 > 제철과일
c = driver.find_element_by_css_selector('#lnbMenu > div.inner_lnb > ul > li:nth-child(3) > a')
c.click()
time.sleep(2)

category_id = 5
crawling(36, category_id)

# 과일,견과,쌀 > 국산과일
c = driver.find_element_by_css_selector('#lnbMenu > div.inner_lnb > ul > li:nth-child(4) > a')
c.click()
time.sleep(2)

category_id = 6
crawling(91, category_id)

# 수산,해산, 건어물 > 제철수산
driver.get('https://www.kurly.com/shop/goods/goods_list.php?category=909010')
time.sleep(3)

category_id = 7
crawling(34, category_id)

# 수산,해산,건어물 > 생선류
c = driver.find_element_by_css_selector('#lnbMenu > div.inner_lnb > ul > li:nth-child(3) > a')
c.click()
time.sleep(2)

category_id = 8
crawling(99, category_id)

# 수산,해산,건어물 > 굴비/반건류
c = driver.find_element_by_css_selector('#lnbMenu > div.inner_lnb > ul > li:nth-child(4) > a')

category_id = 9
crawling(22, category_id)

df = pd.DataFrame(data,
                  columns=['category_id', 'img', 'title', 'sub_title', 'price', 'dc', 'original_price'])

driver.quit()

df.to_csv('data.csv', index=False, encoding='utf-8-sig')
