(ns clockwallet.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::credenciais-usuario
 (fn [db [_ campo]]
   (get-in db [:credenciais-usuario campo])))

(rf/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

(rf/reg-sub
 ::disabled-login-btn
 (fn [db _]
   (let [email-invalido? (empty? (re-matches #".+\@.+\..+" (get-in db [:credenciais-usuario :email])))
         senha-invalida? (< (count (get-in db [:credenciais-usuario :password])) 6)]
     (or email-invalido? senha-invalida?))))

