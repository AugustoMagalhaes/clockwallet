(ns clockwallet.views
  (:require [clockwallet.events :as events]
            [clockwallet.routes :as routes]
            [clockwallet.subs :as subs]
            [re-frame.core :as rf]))
;; home
(defn home-panel
  []
  (let [email (rf/subscribe [::subs/credenciais-usuario :email])
        senha (rf/subscribe [::subs/credenciais-usuario :password])
        btn-login-disabled? (rf/subscribe [::subs/disabled-login-btn])]
    [:section.container-c.login
     [:h2.home-title "ClockWallet"]
     [:img.clock-img {:src "/images/clockwal-removebg-preview.png"}]
     [:section.container-c.inputs-login
      [:label.container-c.login-label "Email: "
       [:input.email {:type "text" :value @email :on-change #(rf/dispatch [::events/atualiza-campo :email (-> % .-target .-value)])}]]
      [:label.container-c.login-label "Senha: "
       [:input.password {:type "password" :value @senha :on-change #(rf/dispatch [::events/atualiza-campo :password (-> % .-target .-value)])}]]]
     [:button.login-btn {:disabled @btn-login-disabled? :on-click #(rf/dispatch [::events/try-login])} "Login"]]))

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
