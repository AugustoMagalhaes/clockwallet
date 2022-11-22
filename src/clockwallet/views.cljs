(ns clockwallet.views
  (:require [clockwallet.events :as events]
            [clockwallet.routes :as routes]
            [clockwallet.subs :as subs]
            [re-frame.core :as re-frame]))
;; home

(defn home-panel []
  [:section.container-c.login
   [:h2 "ClockWallet"]
   [:img.clock-img {:src "clockwallet.png"}]
   [:section.container-c.inputs-login
    [:label.container-c "Email: "
     [:input.email {:type "text"}]]
    [:label.container-c "Senha: "
     [:input.password {:type "password"}]]]
   [:button "Login"]])

(defmethod routes/panels :home-panel [] [home-panel])

;; about

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]

   [:div
    [:a {:on-click #(re-frame/dispatch [::events/navigate :home])}
     "go to Home Page"]]])

(defmethod routes/panels :about-panel [] [about-panel])

;; main

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    (routes/panels @active-panel)))
