UPDATE QUESTION_TB
			SET TITLE = #{title,jdbcType=VARCHAR},
			QA_CON   = #{qaCon,jdbcType=VARCHAR},
			QA_DT   = sysdate,
			STO_ID    = #{stoId,jdbcType=VARCHAR}
		    WHERE QA_NO=#{qaNo,jdbcType=VARCHAR}
	
UPDATE ANSWER_TB
			SET ANS_CON = #{ansCon,jdbcType=VARCHAR},
			ANS_DT   = sysdate,
			QA_NO   = #{qaNo,jdbcType=VARCHAR}
			WHERE ANS_NO =#{ansNo,jdbcType=VARCHAR}
			
	
DELETE FROM QUESTION_TB WHERE QA_NO =#{qaNo,jdbcType=VARCHAR}

DELETE FROM ANSWER_TB WHERE ANS_NO =#{ansNo,jdbcType=VARCHAR}

SELECT * FROM QUESTION_TB;