(ns notion.core
  (:require
    [notion.api.user :as user]
    [notion.api.page :as page]
    [notion.api.block :as block]
    [notion.api.database :as database])
  (:gen-class))

(defn parent
  "Returns the parent of the given object"
  [client object]
  (let [id (:parent-id object),
        type (:parent-type object)
        func (type {:page page/find, :database database/find})]
    (func client id)))

(defn created-by
  "Returns the user who created the object"
  [client object]
  (user/find client (:created-by-id object)))

(defn last-edited-by
  "Returns the last user who edited the object"
  [client object]
  (user/find client (:last-edited-by-id object)))
