(ns clockwallet.wallet.views
  (:require [clockwallet.routes :as routes]
            [re-frame.core :as rf]))

(defn main-header
  []
  [:header {:style {:color "white"}} "header de wallet"])

(defn wallet
  []
  (main-header))

(defmethod routes/panels :wallet-panel [] [wallet])

(prn rf/reg-event-db)