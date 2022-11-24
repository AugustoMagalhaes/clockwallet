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
 ::atualiza-campo
 (fn-traced [db [_ campo valor tem-erro?]]
   (-> db
       (when (:erro db) (dissoc db :erro))
       (assoc-in [:credenciais-usuario campo] valor))))

(defn credenciais-validas?
  [db]
  (= (:credenciais-usuario db) (:credenciais-corretas db)))

(rf/reg-event-fx
 ::try-login
 (fn [{:keys [db]} [_]]
   (if (credenciais-validas? db)
     {:db (dissoc db :erro)
      :fx [[:dispatch [::navigate :wallet]]]}
     {:db (assoc db :erro "Credenciais inválidas")})))



