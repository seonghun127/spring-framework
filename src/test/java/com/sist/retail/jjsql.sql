SELECT * FROM NOTICE_TB

SELECT * FROM COMMENT_TB

SELECT * FROM ORDER_TB
<c:out value="${commentVo.stoNm}" />

SELECT * FROM COMMENT_TB

SELECT * FROM NOTICE_TB

SELECT * FROM PRODUCT_TB

SELECT * FROM STOCK_tB



INSERT INTO COMMENT_TB
VALUES
(
 1,
 '00001',
 '김태열 바보래요~',
 sysdate,
 48
)

SELECT * FROM PRODUCT_TB P 
                LEFT OUTER JOIN ORDER_TB R
                  ON P.PDT_NO=R.PDT_NO
                LEFT OUTER JOIN STOCK_TB T
                  ON P.PDT_NO = T.PDT_NO
                ,MAKER_TB M
                
                SELECT * FROM PRODUCT_TB P 
                LEFT OUTER JOIN STOCK_TB T
                  ON P.PDT_NO = T.PDT_NO
                LEFT OUTER JOIN ORDER_TB R
                  ON P.PDT_NO=R.PDT_NO
                ,MAKER_TB M