(ns clockwallet.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::email-correto
 (fn [db]
   (get-in db [:credenciais-corretas :email])))

(rf/reg-sub
 ::senha-correta
 (fn [db]
   (get-in db [:credenciais-corretas :password])))

(rf/reg-sub
 ::senha
 (fn [db]
   (:password db)))

(rf/reg-sub
 ::email
 (fn [db]
   (:email db)))

(rf/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))
