package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.example.demo.model.DemoForm;

/**
 * コントローラクラス
 * 「@SessionAttributes(types = DemoForm.class)」により、
 * 生成したFormオブジェクトをセッションとしてもたせている
 */
@Controller
@SessionAttributes(types = DemoForm.class)
public class DemoController {

    /**
     * Formオブジェクトを初期化して返却する
     * 
     * @return Formオブジェクト
     */
    @ModelAttribute("demoForm")
    public DemoForm createDemoForm() {
        DemoForm demoForm = new DemoForm();
        // 名前・性別の初期値を設定する
        demoForm.setName("テスト　名前");
        demoForm.setSex("1");
        return demoForm;
    }

    /**
     * 入力画面に遷移する
     * 
     * @param demoForm Formオブジェクト
     * @return 入力画面へのパス
     */
    @GetMapping("Demo/input")
    public String index(DemoForm demoForm) {
        return "Demo/input";
    }

    /**
     * 確認画面に遷移する
     * 
     * @param demoForm Formオブジェクト
     * @return 確認画面へのパス
     */
    @PostMapping("Demo/confirm")
    public String confirm(DemoForm demoForm) {
        return "Demo/confirm";
    }

    /**
     * 完了画面へのリダイレクトパスに遷移する
     * 
     * @return 完了画面へのリダイレクトパス
     */
    @PostMapping("/send")
    public String send() {
        return "redirect:Demo/complete";
    }

    /**
     * 完了画面に遷移する
     * 
     * @param sessionStatus セッションステータス
     * @return 完了画面
     */
    @GetMapping("Demo/complete")
    public String complete(SessionStatus sessionStatus) {
        // セッションオブジェクトを破棄
        sessionStatus.setComplete();
        return "Demo/complete";
    }
}