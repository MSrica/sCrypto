package tools;

public class CheckConditions{

    public CheckConditions(CheckTrendAndMACD asset) {
        // First check if next step is buying or selling
        if (asset.orderPlaced) {
            if (!asset.macdBelowZero && asset.trend) {
                // Function call  for selling certain asset
                // sell();

                // Sets .orderPlaced to "false" so that it can look out for selling next
                asset.orderPlaced = false;

                // "place" fake sell order
                System.out.println("//// Fake sell order placed. ////" + System.currentTimeMillis());
            }
        } else {
            if (asset.macdBelowZero && asset.trend) {
                // Function call for buying certain asset
                // buy();

                // Sets .orderPlaced to "true" so that it can look out for selling next
                asset.orderPlaced = true;

                // "place" fake buy order
                System.out.println("//// Fake buy order placed. ////" + System.currentTimeMillis());
            }
        }
    }
}
