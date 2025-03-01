-- タスクテーブルの初期データ
INSERT INTO tasks (task_no, task_name, registration_date, registrant, detail, task_status)
VALUES
('T001', 'タスク1', NOW(), '登録者1', 'タスク1の詳細', '未完了'),
('T002', 'タスク2', NOW(), '登録者2', 'タスク2の詳細', '完了'),
('T003', 'タスク3', NOW(), '登録者3', 'タスク3の詳細', '未完了'),
('T004', 'タスク4', NOW(), '登録者4', 'タスク4の詳細', '完了'),
('T005', 'タスク5', NOW(), '登録者5', 'タスク5の詳細', '未完了');

-- 社員情報テーブルの初期データ (例：T001 のみ)
INSERT INTO employee_info (task_no, employee_project, employee_number, employee_name, answer_status, answer_remark)
VALUES
('T001', 'JA', 1, 'あああ', '回答済', 'あ'),
('T001', 'JB', 2, 'いいい', '回答済', 'い'),
('T001', 'JC', 3, 'ううう', '未回答', 'う'),
('T001', 'JD', 4, 'えええ', '未回答', 'え');
