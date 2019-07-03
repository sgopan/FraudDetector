package com.afterpay.creditcard;

import com.afterpay.creditcard.transaction.CardTransactionReaderTest;
import com.afterpay.creditcard.transaction.CardTransactionTest;
import com.afterpay.creditcard.util.DateUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DateUtilTest.class, CardTransactionReaderTest.class,
        CardTransactionTest.class, FraudDetectorTest.class})
public class FraudDetectorSuite {
}
