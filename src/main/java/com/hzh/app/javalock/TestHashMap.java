package com.hzh.app.javalock;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hzh", "1");
        map.put("hzh", "2");

        for (int i = 0; i < 30; i++) {
            map.put(i + "", i + "-value");
        }

        HzhNode headNode = null;
        HzhNode tailNode = null;

        for (int id = 1; id <= 50; id++) {
            HzhNode node = new HzhNode();
            node.nodeId = id + "";
            if (tailNode == null)
                headNode = node;
            else
                tailNode.next = node;
            tailNode = node;
        }
        System.out.println(headNode);
//FIXME     结果：
//FIXME             new node()
//FIXME            | |
//FIXME           |   |
//FIXME          |     |
//FIXME        |         |
//FIXME      head       tail
//FIXME
//FIXME
//FIXME
//FIXME
//FIXME             a---------------->b
//FIXME            |                  |
//FIXME           |                   |
//FIXME          |                    |
//FIXME        |                      |
//FIXME      head                    tail
//FIXME
//FIXME
//FIXME              a--------b------->c
//FIXME             |                  |
//FIXME            |                   |
//FIXME           |                    |
//FIXME         |                      |
//FIXME       head                    tail


        //String headJson = JSONObject.toJSONString(headNode);
        //System.out.println(headJson);

        System.out.println("下面开始输出headNode的值=====>");
        HzhNode e = headNode;
        while (e != null) {
            System.out.println(e.getNodeId());
            e = e.next;
        }


//FIXME                    {
//FIXME                      "next": {
//FIXME                        "next": {
//FIXME                          "next": {
//FIXME                            "next": {
//FIXME                              "next": {
//FIXME                                "nodeId": "5"
//FIXME                              },
//FIXME                              "nodeId": "4"
//FIXME                            },
//FIXME                            "nodeId": "3"
//FIXME                          },
//FIXME                          "nodeId": "2"
//FIXME                        },
//FIXME                        "nodeId": "1"
//FIXME                      },
//FIXME                      "nodeId": "0"
//FIXME                    }
    }

    @Data
    @ToString
    public static class HzhNode {
        private String nodeId;
        private HzhNode next;
    }
}


//{
//  "next": {
//    "next": {
//      "next": {
//        "next": {
//          "next": {
//            "next": {
//              "next": {
//                "next": {
//                  "next": {
//                    "next": {
//                      "next": {
//                        "next": {
//                          "next": {
//                            "next": {
//                              "next": {
//                                "next": {
//                                  "next": {
//                                    "next": {
//                                      "next": {
//                                        "next": {
//                                          "next": {
//                                            "next": {
//                                              "next": {
//                                                "next": {
//                                                  "next": {
//                                                    "next": {
//                                                      "next": {
//                                                        "next": {
//                                                          "next": {
//                                                            "nodeId": "30"
//                                                          },
//                                                          "nodeId": "29"
//                                                        },
//                                                        "nodeId": "28"
//                                                      },
//                                                      "nodeId": "27"
//                                                    },
//                                                    "nodeId": "26"
//                                                  },
//                                                  "nodeId": "25"
//                                                },
//                                                "nodeId": "24"
//                                              },
//                                              "nodeId": "23"
//                                            },
//                                            "nodeId": "22"
//                                          },
//                                          "nodeId": "21"
//                                        },
//                                        "nodeId": "20"
//                                      },
//                                      "nodeId": "19"
//                                    },
//                                    "nodeId": "18"
//                                  },
//                                  "nodeId": "17"
//                                },
//                                "nodeId": "16"
//                              },
//                              "nodeId": "15"
//                            },
//                            "nodeId": "14"
//                          },
//                          "nodeId": "13"
//                        },
//                        "nodeId": "12"
//                      },
//                      "nodeId": "11"
//                    },
//                    "nodeId": "10"
//                  },
//                  "nodeId": "9"
//                },
//                "nodeId": "8"
//              },
//              "nodeId": "7"
//            },
//            "nodeId": "6"
//          },
//          "nodeId": "5"
//        },
//        "nodeId": "4"
//      },
//      "nodeId": "3"
//    },
//    "nodeId": "2"
//  },
//  "nodeId": "1"
//}
