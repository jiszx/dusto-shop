CREATE OR REPLACE PROCEDURE PRODUCT IS
  FLAG NUMBER := 0;
BEGIN
  FOR REC IN (SELECT * FROM T_MATERIAL_BASE) LOOP
    FOR CONTRACT IN (SELECT CMCCL.*
                       FROM C_MERCH_CUST_CONTRACT_LINES CMCCL,
                            C_MERCH_CUST_CONTRACT       CMCC
                      WHERE CMCCL.CONTRACT_ID = CMCC.ID
                        AND CMCC.STATES = '4') LOOP
      --判断物料是否存在与客户产品表中
      BEGIN
        SELECT NVL(1, 0)
          INTO FLAG
          FROM C_MERCH_CUST_PRODUCT CMCP
         WHERE CMCP.MATERIAL_ID = REC.SAP_ID
           AND CMCP.CONTRACT_ID = CONTRACT.CONTRACT_ID
           AND CMCP.CONTRACT_LINEID = CONTRACT.ID;
      EXCEPTION
        WHEN OTHERS THEN
          FLAG := 0;
      END;
      --FLAG等于0表示不存在
      IF FLAG = 0 THEN
        --根据代理类型判断不存在与客户产品表中的物料是否属于客户代理范围内的物料
        IF CONTRACT.AGENT_ID = REC.I_PACKAGE THEN
          FLAG := 0;
        END IF;
        IF CONTRACT.AGENT_ID = REC.SERIES THEN
          FLAG := 0;
        END IF;
        IF CONTRACT.AGENT_ID = REC.ATTRIBUTE1 THEN
          FLAG := 0;
        END IF;
        IF CONTRACT.AGENT_ID = 'SY' THEN
          FLAG := 0;
        END IF;
      
      END IF;
      --物料不存在与客户产品表中，并且属于客户代理范围内，新增到客户产品表中
      IF FLAG = 0 THEN
        INSERT INTO C_MERCH_CUST_PRODUCT
          (ID,
           MERCH_CUST_ID,
           MATERIAL_ID,
           ORGANIZATION_ID,
           CONTRACT_ID,
           CONTRACT_LINEID,
           CREATE_TS,
           CREATE_OID,
           STATES)
          (SELECT SEQ_MERCH_PRODUCT_ID.NEXTVAL,
                  CMCC.MERCH_CUST_ID,
                  REC.SAP_ID,
                  CMCC.ORGANIZATION_ID,
                  CMCC.ID,
                  CONTRACT.ID,
                  SYSDATE,
                  CMCC.CREATE_OID,
                  '3'
             FROM C_MERCH_CUST_CONTRACT CMCC
            WHERE 1 = 1
              AND CMCC.ID = CONTRACT.CONTRACT_ID);
        COMMIT;
        --更新客户库存表
        INSERT INTO C_MERCH_CUST_PROUDCT_INV
          (ID,
           MERCH_CUST_ID,
           ORGANIZATION_ID,
           INV_NUM,
           FROZEN_NUM,
           CREATE_TS,
           CREATE_OID,
           CONTRACT_ID,
           MATERIAL_ID)
          (SELECT SEQ_MERCH_INV_ID.NEXTVAL,
                  CMCC.MERCH_CUST_ID,
                  CMCC.ORGANIZATION_ID,
                  0,
                  0,
                  SYSDATE,
                  CMCC.CREATE_OID,
                  CMCC.ID,
                  CMCP.MATERIAL_ID
             FROM C_MERCH_CUST_CONTRACT CMCC,
                  C_MERCH_CUST_PRODUCT  CMCP,
                  C_MERCH_CUST_BASE     CMCB
            WHERE CMCC.ID = CMCP.CONTRACT_ID
              AND CMCB.ID = CMCC.MERCH_CUST_ID
              AND CMCC.STATES = '4'
              AND CMCB.STATES = '3'
              AND CMCB.CUST_TYPE = '2'
              AND CMCC.ID = CONTRACT.CONTRACT_ID
              AND NOT EXISTS
            (SELECT 1
                     FROM C_MERCH_CUST_PRODUCT     CMCP2,
                          C_MERCH_CUST_PROUDCT_INV CMCPI,
                          C_MERCH_CUST_BASE CMCB
                    WHERE CMCP2.MATERIAL_ID = CMCPI.MATERIAL_ID
                      AND CMCP2.MERCH_CUST_ID = CMCPI.MERCH_CUST_ID
                      AND CMCB.ID= CMCPI.MERCH_CUST_ID
                      AND CMCB.STATES='3'
                      AND CMCB.CUST_TYPE='2'
                      AND CMCP2.STATES = '4'));
      
        --更新客户库存期间表
        INSERT INTO C_MERCH_CUST_PRODUCT_BALANCES
          (ID,
           MERCH_CUST_ID,
           ORGANIZATION_ID,
           PERIOD,
           YTD,
           D_NUM,
           C_NUM,
           PTD,
           STATES,
           CONTRACT_ID,
           MATERIAL_ID)
          (SELECT SEQ_MERCH_BALANCE_ID.NEXTVAL,
                  CMCC.MERCH_CUST_ID,
                  CMCC.ORGANIZATION_ID,
                  TO_CHAR(SYSDATE, 'YYYY-MM'),
                  0,
                  0,
                  0,
                  0,
                  '1',
                  CMCC.ID,
                  CMCP.MATERIAL_ID
             FROM C_MERCH_CUST_CONTRACT CMCC,
                  C_MERCH_CUST_PRODUCT  CMCP,
                  C_MERCH_CUST_BASE     CMCB
            WHERE 1 = 1
              AND CMCC.ID = CMCP.CONTRACT_ID
              AND CMCB.ID = CMCC.MERCH_CUST_ID
              AND CMCB.STATES = '3'
              AND CMCB.CUST_TYPE = '2'
              AND CMCC.STATES = '4'
              AND CMCC.ID = CONTRACT.CONTRACT_ID
              AND NOT EXISTS
            (SELECT 1
                     FROM C_MERCH_CUST_PRODUCT          CMCP2,
                          C_MERCH_CUST_PRODUCT_BALANCES CMCPI,
                          C_MERCH_CUST_BASE             CMCB
                    WHERE CMCP2.MATERIAL_ID = CMCPI.MATERIAL_ID
                      AND CMCP2.MERCH_CUST_ID = CMCPI.MERCH_CUST_ID
                      AND CMCPI.MERCH_CUST_ID = CMCB.ID
                      AND CMCB.STATES = '3'
                      AND CMCB.CUST_TYPE = '2'
                      AND CMCP2.STATES = '4'));
        COMMIT;
      END IF;
    END LOOP;
  END LOOP;
  COMMIT;
END PRODUCT;
