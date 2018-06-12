(ns braid.video-embeds.core
  "If a message contains a link to a video, displays the video as an embed"
  (:require
    [braid.core.api :as core]
    #?@(:cljs
         [[braid.embeds.api :as embeds]
          [braid.video-embeds.impl :as impl]])))

(defn init! []
  #?(:cljs
     (do
       (embeds/register-embed!
         {:handler impl/handler
          :styles impl/styles
          :priority 1}))))
