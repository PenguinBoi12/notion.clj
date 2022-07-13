(ns notion.database
  (:require [notion.api.utils :as api]))

(defn create! [client database])

(defn save! [client database])

(defn delete! [client database]
  (let [database_id (:id database)]
    (api/delete! client :database database_id)))
