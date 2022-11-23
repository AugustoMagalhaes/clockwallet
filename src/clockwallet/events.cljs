(ns clockwallet.events
  (:require [clockwallet.db :as db]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(rf/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(rf/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))

(rf/reg-event-db
 ::update-email
 (fn-traced [db [_ email]]
   (assoc db :email email)))

(rf/reg-event-db
 ::update-senha
 (fn-traced [db [_ password]]
   (assoc db :password password)))

(rf/reg-event-db
 ::try-login
 (fn-traced [db [_ email password]]
            (if
             (and
              (= (:email db) (-> db :credenciais-corretas :email))
              (= (:password db) (-> db :credenciais-corretas :password)))
              (prn "logou")
              (prn "nao logou"))))
