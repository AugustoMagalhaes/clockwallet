(ns clockwallet.wallet.views
  (:require [clockwallet.routes :as routes]
            [re-frame.core :as rf]))

(defn main-header
  []
  [:header {:style {:color "white"}} "header de wallet"])

(defn wallet-panel
  []
  (main-header))

(defmethod routes/panels :wallet-panel [] [wallet-panel])

(prn rf/reg-event-db)