(ns braid.client.quests.handler
  (:require [braid.client.quests.helpers :as helpers]
            [braid.client.state.handler.core :refer [handler]]
            [braid.client.quests.list :refer [quests-by-id]]))

(defn maybe-increment-quest-record [state quest-record event args]
  (let [quest (quests-by-id (quest-record :quest-record/quest-id))
        inc-progress? ((quest :quest/listener) state [event args])]
    (if inc-progress?
      (handler state [:quests/increment-quest {:quest-record-id (quest-record :quest-record/id)}])
      state)))

(defn quests-handler [state [event args]]
  (reduce (fn [state quest-record]
            (maybe-increment-quest-record state quest-record event args))
          state
          (helpers/get-active-quest-records state)))

