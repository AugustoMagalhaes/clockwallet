(ns clockwallet.views
  (:require [clockwallet.events :as events]
            [clockwallet.routes :as routes]
            [clockwallet.subs :as subs]
            [re-frame.core :as rf]))
;; home

(defn home-panel []
  [:section.container-c.login
   [:h2.home-title "ClockWallet"]
   [:img.clock-img {:src "/images/clockwal-removebg-preview.png"}]
   [:section.container-c.inputs-login
    [:label.container-c.login-label "Email: "
     [:input.email {:type "text"} ]]
    [:label.container-c.login-label "Senha: "
     [:input.password {:type "password"}]]]
   [:button.login-btn "Login"]])

(defmethod routes/panels :home-panel [] [home-panel])

;; about

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]

   [:div
    [:a {:on-click #(rf/dispatch [::events/navigate :home])}
     "go to Home Page"]]])

(defmethod routes/panels :about-panel [] [about-panel])

;; main

(defn main-panel []
  (let [active-panel (rf/subscribe [::subs/active-panel])]
    (routes/panels @active-panel)))
