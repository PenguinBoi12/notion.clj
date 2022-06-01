(ns notion.client
  (:require
    [notion.api.utils :as api]
    [notion.config :refer [load-default-config]]
    [notion.types.user :refer [build-user]]
    [notion.types.page :refer [build-page]]
    [notion.types.database :refer [build-database]]
    [notion.types.client :refer :all]))

(defn init! [& config-path]
  (let [config (load-default-config)]
    (->Client config)))

(defn search
  ([client query options]
    (api/search client query options))
  ([client query]
    (api/search client query {})))

(defn user [client id]
  (build-user (api/fetch client :user id)))

(defn users [client]
  (map build-user (api/fetch client :user)))

(defn database [client id]
  (build-database (api/fetch client :database id)))

(defn page [client id]
  (build-page (api/fetch client :page id)))

(defn parent [client model]
  (let [id (:parent-id model),
        type (:parent-type model)
        func (type {:page page, :database database})]
    (func client id)))

(defn created-by [client model]
  (user client (:created-by-id model)))

(defn last-edited-by [client model]
  (user client (:last-edited-by-id model)))
