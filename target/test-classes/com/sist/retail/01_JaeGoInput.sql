SELECT * FROM STOCK_TB
SELECT * FROM PRODUCT_TB
SELECT * FROM STORE_TB

INSERT INTO STOCK_TB(PDT_NO,STO_ID,STK_CNT)
SELECT PDT_NO,'11111',9999 FROM PRODUCT_TB WHERE PDT_NO>'00000'

COMMIT

DELETE FROM STOCK_TB WHERE PDT_NO>'00000'